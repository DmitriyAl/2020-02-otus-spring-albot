import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "./product.service";
import {Note} from "../../model/note";
import {Product} from "../../model/product";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NoteService} from "../../service/note.service";
import {NoteDto} from "../../dto/noteDto";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.less']
})
export class ProductComponent implements OnInit {
  editMode: boolean = false;
  newProductMode: boolean = false;
  product: Product;
  notes: Note[];
  form: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private service: ProductService, private fb: FormBuilder, private noteService: NoteService) {
    this.form = this.fb.group({
      comment: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    const strId = this.activatedRoute.snapshot.paramMap.get('id');
    if (isNaN(+strId) && strId === 'new') {
      this.initNewProduct();
    } else if (!isNaN(+strId)) {
      this.loadProductFromDB(strId);
    } else {
      this.goToProducts();
    }
  }

  private initNewProduct() {
    this.product = new Product();
    this.notes = [];
    this.newProductMode = true;
    this.editMode = true;
  }

  private loadProductFromDB(strId: string) {
    this.getProduct(+strId);
    this.getNotes(+strId);
  }

  private getProduct(id: number) {
    this.service.getProduct(id)
      .subscribe(product => this.product = new Product(product), error => console.log(error));
  }

  private getNotes(id: number) {
    this.noteService.getNotesToProduct(id)
      .subscribe(notes => this.notes = notes.map(n => new Note(n)), error => console.log(error));
  }

  switchToEditMode(editMode: boolean) {
    this.editMode = editMode;
    if (this.newProductMode && !editMode) {
      this.goToProducts();
    }
  }

  updateProduct() {
    this.service.updateProduct(this.product.productDto)
      .subscribe(p => {
        this.product = new Product(p);
        this.editMode = false;
        if (this.newProductMode) {
          this.goToProducts();
        }
      }, error => console.log(error));
  }

  private goToProducts() {
    this.router.navigate(["/products"])
  }

  submit(value: any) {
    const dto = new NoteDto();
    dto.comment = value.comment;
    dto.product = this.product.productDto;
    this.noteService.addComment(dto)
      .subscribe(n => {
        this.getNotes(this.product.productDto.id);
        this.form.reset();
      }, error => console.log(error))
  }

  removeNote(note: Note) {
    this.noteService.removeComment(note.noteDto)
      .subscribe(() => this.getNotes(this.product.productDto.id), error => console.log(error));
  }
}
