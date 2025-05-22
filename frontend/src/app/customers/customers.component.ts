import { Component, OnInit } from '@angular/core';
import { CustomerControllerService } from '../core/api/api/services/customerController.service';
import { CustomerDto } from '../core/api/api/models/customerDto';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CreateCustomerDialogComponent } from './create-customer-dialog.component';
import { RentalControllerService } from '../core/api/api/services/rentalController.service';
import { RentCarDialogComponent } from './rent-car-dialog.component';
import { EditCustomerDialogComponent } from './edit-customer-dialog.component';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [CommonModule, FormsModule, MatTableModule, MatButtonModule, MatIconModule, MatDialogModule, MatFormFieldModule, MatInputModule],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.scss'
})
export class CustomersComponent implements OnInit {
  customers: CustomerDto[] = [];
  filteredCustomers: CustomerDto[] = [];
  displayedColumns: string[] = ['firstname', 'lastname', 'email', 'actions'];
  filterFirstname = '';
  filterLastname = '';

  constructor(private customerService: CustomerControllerService,
    private rentalService: RentalControllerService,
    private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers() {
    this.customerService.getCustomers('body', false, { httpHeaderAccept: '*/*' }).subscribe(data => {
      if (data instanceof Blob) {
        data.text().then(text => {
          const arr = JSON.parse(text);
          this.customers = arr;
          this.applyFilter();
        });
      } else {
        this.customers = data;
        this.applyFilter();
      }
    });
  }

  applyFilter() {
    this.filteredCustomers = this.customers.filter(kunde => {
      const firstname = (kunde.firstname || '').toLowerCase();
      const lastname = (kunde.lastname || '').toLowerCase();
      return firstname.includes(this.filterFirstname.toLowerCase()) &&
             lastname.includes(this.filterLastname.toLowerCase());
    });
  }

  create() {
    const dialogRef = this.dialog.open(CreateCustomerDialogComponent, {
      minHeight: '400px',
      minWidth: '400px',
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadCustomers();
      }
    });
  }

  rent(customer: CustomerDto) {
    const dialogRef = this.dialog.open(RentCarDialogComponent, {
      minHeight: '400px',
      minWidth: '400px',
      data: { customerId: customer.customerId }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const rental = {
          customerId: result.customerId,
          carId: result.carId,
          rentalBegin: result.rentalBegin,
          rentalEnd: result.rentalEnd
        };
        this.rentalService.createRental(rental).subscribe(() => {
          this.loadCustomers();
        });
      }
    });
  }

  edit(customer: CustomerDto) {
    const dialogRef = this.dialog.open(EditCustomerDialogComponent, {
      minHeight: '400px',
      minWidth: '400px',
      data: { customer: customer }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadCustomers();
      }
    });
  }

  delete(customer: CustomerDto) {
    if (customer.customerId) {
      this.customerService.deleteCustomer(customer.customerId).subscribe(() => this.loadCustomers());
    }
  }
}
