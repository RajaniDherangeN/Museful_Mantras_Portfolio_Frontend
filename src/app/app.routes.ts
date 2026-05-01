import { RouterModule, Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { ArtGalleryComponent } from './pages/art-details/art-details';
import {Contact} from './pages/contact/contact';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    {path:"",component:Home},
    {path: 'art-details', component: ArtGalleryComponent },
    {path:'contact',component:Contact}
];

@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
})
export class AppRoutingModule{}
