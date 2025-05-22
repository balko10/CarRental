import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerControllerService } from '../core/api/api/services/customerController.service';
import { CustomerDto } from '../core/api/api/models/customerDto';

@Component({
  selector: 'app-create-customer-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  template: `
    <h2 mat-dialog-title style="margin-bottom: 1.5rem; text-align:center; font-weight:600;">Kunde anlegen</h2>
    <form (ngSubmit)="onSave()" #form="ngForm" class="customer-dialog-form">
      <div class="customer-dialog-grid">
        <mat-form-field appearance="outline">
          <mat-label>Vorname</mat-label>
          <input matInput [(ngModel)]="customer.firstname" name="firstname" required autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Nachname</mat-label>
          <input matInput [(ngModel)]="customer.lastname" name="lastname" required autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Email</mat-label>
          <input matInput [(ngModel)]="customer.email" name="email" required autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Telefon</mat-label>
          <input matInput [(ngModel)]="customer.phone" name="phone" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Strasse</mat-label>
          <input matInput [(ngModel)]="customer.streetName" name="address" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Hausnummer</mat-label>
          <input matInput [(ngModel)]="customer.houseNumber" name="streetNumber" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>PLZ</mat-label>
          <input matInput [(ngModel)]="customer.postCode" name="zipCode" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Stadt</mat-label>
          <input matInput [(ngModel)]="customer.city" name="city" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Führerschein</mat-label>
          <input matInput [(ngModel)]="customer.drivingLicense" name="drivingLicense" autocomplete="off">
        </mat-form-field>
      </div>
      <div style="display:flex; justify-content:flex-end; gap:1rem; margin-top:1.5rem;">
        <button mat-stroked-button type="button" (click)="onCancel()">Abbrechen</button>
        <button mat-raised-button color="primary" type="submit" [disabled]="form.invalid || loading">
          {{ loading ? 'Speichern...' : 'Speichern' }}
        </button>
      </div>
    </form>
  `,
  styles: [`
    .customer-dialog-form {
      min-width: 480px;
      max-width: 700px;
      margin: 0 auto;
      padding: 0 2rem 1rem 2rem;
    }
    .customer-dialog-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 1.5rem 2rem;
    }
    @media (max-width: 800px) {
      .customer-dialog-form {
        min-width: 0;
        max-width: 100vw;
        padding: 0 0.5rem 1rem 0.5rem;
      }
      .customer-dialog-grid {
        grid-template-columns: 1fr;
      }
    }
    mat-form-field {
      width: 100%;
    }
  `]
})
export class CreateCustomerDialogComponent {
  customer: Partial<CustomerDto> = {};
  loading = false;

  constructor(
    private dialogRef: MatDialogRef<CreateCustomerDialogComponent>,
    private customerService: CustomerControllerService
  ) {}

  onSave() {
    if (!this.customer.firstname || !this.customer.lastname || !this.customer.email) return;
    this.loading = true;
    this.customerService.createCustomer(this.customer as CustomerDto).subscribe({
      next: () => {
        this.dialogRef.close(true);
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  onCancel() {
    this.dialogRef.close(false);
  }
}
