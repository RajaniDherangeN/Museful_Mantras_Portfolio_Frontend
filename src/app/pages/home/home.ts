import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {
  Component,
  OnInit,
  AfterViewInit,
  OnDestroy,
  HostListener,
  ChangeDetectorRef,
  NgZone
} from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterLink, HttpClientModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home implements OnInit, AfterViewInit, OnDestroy {

  constructor(
    private http: HttpClient,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone
  ) {}

  artworks: any[] = [];
  transforms: string[] = [];

  angle = 0;
  targetAngle = 0;
  step = 0;

  activeIndex = 0;
  activeTitle = 'Artwork';
  activeDesc = '';

  private rafId = 0;

  get radius() {
    return Math.max(180, this.artworks.length * 45);
  }

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/random-artworks')
      .subscribe(data => {
        this.artworks = data;
        this.step = (Math.PI * 2) / this.artworks.length;
        this.transforms = new Array(data.length).fill('');

        if (this.artworks.length > 0) {
          this.showInfo(0);
        }

        // start animation only after data loads
        this.ngZone.runOutsideAngular(() => this.animate());
      });
  }

  ngAfterViewInit() {}

  ngOnDestroy() {
    cancelAnimationFrame(this.rafId);
  }

  showInfo(index: number) {
    if (!this.artworks[index]) return;

    this.activeTitle = this.artworks[index].title;
    this.activeDesc = this.artworks[index].description;
  }

  layout() {
   if (!this.artworks.length || !this.step) return;

  let newActiveIndex =
    Math.round(this.angle / this.step) % this.artworks.length;

  if (newActiveIndex < 0) {
    newActiveIndex += this.artworks.length;
  }

  this.artworks.forEach((_, index) => {
    const angle = index * this.step + this.angle;
    const x = Math.sin(angle) * this.radius;
    const z = Math.cos(angle) * this.radius;
    const scale = index === newActiveIndex ? 1.9 : 1;

    this.transforms[index] =
      `translate3d(${x}px,-90px,${z}px) rotateY(${angle}rad) scale(${scale})`;
  });

  if (newActiveIndex !== this.activeIndex) {
    this.activeIndex = newActiveIndex;

    this.ngZone.run(() => {
      this.showInfo(this.activeIndex);
      this.cdr.markForCheck();
    });
  }
  }

  animate() {
    this.angle += (this.targetAngle - this.angle) * 0.15;
    this.layout();
    this.rafId = requestAnimationFrame(() => this.animate());
  }

  next() {
    this.targetAngle += this.step;
  }

  prev() {
    this.targetAngle -= this.step;
  }

  goToContact() {
    this.router.navigate(['/contact']);
  }

  @HostListener('document:mousemove', ['$event'])
  onMouseMove(event: MouseEvent) {
    const x = (event.clientX / window.innerWidth - 0.5) * 12;
    const y = (event.clientY / window.innerHeight - 0.5) * 12;

    const room = document.querySelector('.room') as HTMLElement;

    if (room) {
      room.style.transform = `rotateY(${x}deg) rotateX(${-y}deg)`;
    }
  }
}