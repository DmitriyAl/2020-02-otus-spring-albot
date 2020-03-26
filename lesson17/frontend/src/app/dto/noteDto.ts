import {ProductDto} from "./productDto";

export class NoteDto{
  public id: number;
  public comment: string;
  public product: ProductDto;
  public rate: number;
}
