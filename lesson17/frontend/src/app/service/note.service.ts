import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {NoteDto} from "../dto/noteDto";
import {api} from "../utils/api";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private http: HttpClient) {
  }

  public getNotesToProduct(productId: number): Observable<NoteDto[]> {
    return this.http.get<NoteDto[]>(api.notes + `/${productId}`)
  }

  public addComment(note: NoteDto): Observable<NoteDto> {
    return this.http.post<NoteDto>(api.notes, note)
  }

  public removeComment(note: NoteDto): Observable<void> {
    const params = new HttpParams().append('id', note.id.toString());
    return this.http.delete<void>(api.notes, {params});
  }
}
