import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CarControllerService } from '../core/api/api/services/carController.service';
import { CarDto } from '../core/api/api/models/carDto';

@Component({
  selector: 'app-edit-car-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  template: `
    <h2 mat-dialog-title style="margin-bottom: 1.5rem; text-align:center; font-weight:600; letter-spacing:0.02em;">Auto bearbeiten</h2>
    <form (ngSubmit)="onSave()" #form="ngForm" class="car-dialog-form">
      <div class="car-dialog-grid">
        <mat-form-field appearance="outline">
          <mat-label>Marke</mat-label>
          <input matInput [(ngModel)]="car.brand" name="marke" required autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Modell</mat-label>
          <input matInput [(ngModel)]="car.model" name="modell" required autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Kennzeichen</mat-label>
          <input matInput [(ngModel)]="car.licensePlate" name="kennzeichen" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Farbe</mat-label>
          <input matInput [(ngModel)]="car.color" name="farbe" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Baujahr</mat-label>
          <input matInput type="number" [(ngModel)]="car.constructionYear" name="baujahr" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Fahrgestellnummer</mat-label>
          <input matInput [(ngModel)]="car.chassisNumber" name="chassisNumber" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Kategorie</mat-label>
          <input matInput [(ngModel)]="car.category" name="category" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Kilometerstand</mat-label>
          <input matInput type="number" [(ngModel)]="car.mileage" name="mileage" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Status</mat-label>
          <input matInput [(ngModel)]="car.status" name="status" autocomplete="off">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Preis pro Tag (€)</mat-label>
          <input matInput type="number" [(ngModel)]="car.dailyPrice" name="dailyPrice" autocomplete="off" required>
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
    .car-dialog-form {
      min-width: 480px;
      max-width: 700px;
      margin: 0 auto;
      padding: 0 2rem 1rem 2rem;
    }
    .car-dialog-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 1.5rem 2rem;
    }
    @media (max-width: 800px) {
      .car-dialog-form {
        min-width: 0;
        max-width: 100vw;
        padding: 0 0.5rem 1rem 0.5rem;
      }
      .car-dialog-grid {
        grid-template-columns: 1fr;
      }
    }
    mat-form-field {
      width: 100%;
    }
  `]
})
export class EditCarDialogComponent {
  car: CarDto;
  loading = false;

  constructor(
    private dialogRef: MatDialogRef<EditCarDialogComponent>,
    private carService: CarControllerService,
    @Inject(MAT_DIALOG_DATA) public data: { car: CarDto }
  ) {
    this.car = data.car;
    console.log(this.car);
  }

  onSave() {
    if (!this.car.carId || !this.car.brand || !this.car.model || !this.car.dailyPrice) return;
    this.loading = true;
    this.carService.updateCar(this.car.carId, this.car as CarDto).subscribe({
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
