import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomersComponent } from './customers/customers.component';
import { CarsComponent } from './cars/cars.component';
import { RentalsComponent } from './rentals/rentals.component';

export const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'customers', loadComponent: () => import('./customers/customers.component').then(m => m.CustomersComponent) },
  { path: 'cars', loadComponent: () => import('./cars/cars.component').then(m => m.CarsComponent) },
  { path: 'rentals', loadComponent: () => import('./rentals/rentals.component').then(m => m.RentalsComponent) },
];
