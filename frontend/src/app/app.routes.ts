import { Routes } from '@angular/router';

import {MoviesPageComponent} from "./features/movies/movies-page.component";

export const routes: Routes = [
    {
        path: '',
        component: MoviesPageComponent,
        pathMatch: 'full',
    },
    {
        path: '**',
        redirectTo: '',
    }
];
