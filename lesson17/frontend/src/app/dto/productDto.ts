import {NoteDto} from "./noteDto";

export class ProductDto {
  public id: number;
  public name: string;
  public description: string;
  public notes: NoteDto[];
}
