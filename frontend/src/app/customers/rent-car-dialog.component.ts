import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CarControllerService } from '../core/api/api/services/carController.service';
import { CarDto } from '../core/api/api/models/carDto';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-rent-car-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule],
  templateUrl: './rent-car-dialog.component.html',
  styleUrl: './rent-car-dialog.component.scss'
})
export class RentCarDialogComponent {
  cars: CarDto[] = [];
  selectedCarId: number | null = null;
  rentalBegin: string = '';
  rentalEnd: string = '';
  dailyPrice: number | null = null;

  constructor(
    private carService: CarControllerService,
    public dialogRef: MatDialogRef<RentCarDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { customerId: number }
  ) {
    this.loadCars();
  }

  loadCars() {
    this.carService.getCars('body', false, { httpHeaderAccept: '*/*' }).subscribe(data => {
      if (data instanceof Blob) {
        data.text().then(text => {
          this.cars = JSON.parse(text);
        });
      } else {
        this.cars = data;
      }
    });
  }

  onCarChange(carId: number) {
    const car = this.cars.find(c => c.carId === carId);
    this.dailyPrice = car?.dailyPrice ?? null;
  }

  save() {
    if (this.selectedCarId && this.rentalBegin && this.rentalEnd) {
      this.dialogRef.close({
        customerId: this.data.customerId,
        carId: this.selectedCarId,
        rentalBegin: this.rentalBegin,
        rentalEnd: this.rentalEnd,
        dailyPrice: this.dailyPrice
      });
    }
  }

  cancel() {
    this.dialogRef.close();
  }
}
