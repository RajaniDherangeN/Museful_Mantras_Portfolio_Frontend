import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'; 
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-art-gallery',
  templateUrl: './art-details.html',
  styleUrls: ['./art-details.css'],
  standalone: true, 
  imports: [CommonModule, HttpClientModule]
})
export class ArtGalleryComponent implements OnInit {

  constructor(private http: HttpClient) {}  

  Category = ['Desserts', 'Drinks', 'Coffee', 'Savory'];
  selectedCategory = 'Desserts';
  selectedArt: any = null;

  artworks: any[] = [];

  get filteredArt() {
    return this.artworks.filter(a => a.category === this.selectedCategory);
  }

  selectPeriod(period: string) {
    this.selectedCategory = period;
  }

  @ViewChild('myPopup') myPopup!: ElementRef;

  openPopup(art: any) {
    this.selectedArt = art;
  }

  closePopup() {
    this.selectedArt = null;
  }

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/artworks')
      .subscribe(data => {
        this.artworks = data;
      });
  }
}