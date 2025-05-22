import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CarControllerService } from '../core/api/api/services/carController.service';
import { CarDto } from '../core/api/api/models/carDto';
import { CreateCarDialogComponent } from './create-car-dialog.component';
import { EditCarDialogComponent } from './edit-car-dialog.component';

@Component({
  selector: 'app-autoverwaltung',
  standalone: true,
  imports: [CommonModule, FormsModule, MatCardModule, MatButtonModule, MatIconModule, MatDialogModule, MatFormFieldModule, MatInputModule],
  templateUrl: './cars.component.html',
  styleUrl: './cars.component.scss'
})
export class CarsComponent implements OnInit {
  cars: CarDto[] = [];
  filteredCars: CarDto[] = [];
  filterBrand = '';
  filterModel = '';

  constructor(private carService: CarControllerService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadCars();
  }

  loadCars() {
    this.carService.getCars('body', false, { httpHeaderAccept: '*/*' }).subscribe(data => {
      if (data instanceof Blob) {
        data.text().then(text => {
          const arr = JSON.parse(text);
          this.cars = arr;
          this.applyFilter();
        });
      } else {
        this.cars = data;
        this.applyFilter();
      }
    });
  }

  applyFilter() {
    this.filteredCars = this.cars.filter(auto => {
      const brand = (auto.brand || '').toLowerCase();
      const model = (auto.model || '').toLowerCase();
      return brand.includes(this.filterBrand.toLowerCase()) &&
             model.includes(this.filterModel.toLowerCase());
    });
  }

  create() {
    const dialogRef = this.dialog.open(CreateCarDialogComponent, {
      minHeight: '400px',
      minWidth: '420px'
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadCars();
      }
    });
  }


  edit(car: CarDto) {
    const dialogRef = this.dialog.open(EditCarDialogComponent, {
      minHeight: '400px',
      minWidth: '420px',
      data: {car: car}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadCars();
      }
    });
  }

  delete(car: CarDto) {
    if (car.carId) {
      this.carService.deleteCar(car.carId).subscribe(() => this.loadCars());
    }
  }
}
