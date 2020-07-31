import {NoteDto} from "../dto/noteDto";

export class Note {
  noteDto: NoteDto;

  constructor(noteDto: NoteDto) {
    this.noteDto = noteDto;
  }
}
