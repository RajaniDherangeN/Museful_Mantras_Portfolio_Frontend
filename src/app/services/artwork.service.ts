import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Artwork {
  id?: number;
  title: string;
  imageUrl: string;
  description: string;
  likes: number;
  price: number;
}

@Injectable({
  providedIn: 'root' // ✅ Must be root so HttpClient can be injected
})
export class ArtService {
  private apiUrl = 'http://localhost:8080/api/artworks';

  constructor(private http: HttpClient) {} // ✅ HttpClient will be injected

  getArtworks(): Observable<Artwork[]> {
    return this.http.get<Artwork[]>(this.apiUrl);
  }

  likeArtwork(id: number): Observable<Artwork> {
    return this.http.put<Artwork>(`${this.apiUrl}/${id}/like`, {});
  }
}
