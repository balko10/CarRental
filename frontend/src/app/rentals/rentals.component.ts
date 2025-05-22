import { Component, OnInit } from '@angular/core';
import { RentalControllerService } from '../core/api/api/services/rentalController.service';
import { RentalDto } from '../core/api/api/models/rentalDto';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-rentals',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatIconModule],
  templateUrl: './rentals.component.html',
  styleUrl: './rentals.component.scss'
})
export class RentalsComponent implements OnInit {
  rentals: RentalDto[] = [];
  displayedColumns: string[] = [
    'rentalId', 'carBrandAndModel', 'carLicensePlate', 'customerFullName', 'rentalBegin', 'rentalEnd', 'returnDate', 'mileageBegin', 'mileageEnd'
  ];

  constructor(private rentalService: RentalControllerService) {}

  ngOnInit(): void {
    this.loadRentals();
  }

  loadRentals() {
    this.rentalService.getRentals('body', false, { httpHeaderAccept: '*/*' }).subscribe(data => {
      if (data instanceof Blob) {
        data.text().then(text => {
          const arr = JSON.parse(text);
          this.rentals = arr;
        });
      } else {
        this.rentals = data;
      }
    });
  }
}
