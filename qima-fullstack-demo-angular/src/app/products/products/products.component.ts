import { AuthService } from './../../auth/service/auth.service';
import { Product } from './../model/product';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationDialogComponent } from '../../shared/components/confirmation-dialog/confirmation-dialog/confirmation-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

  products$: Observable<Product[]> ;

  displayedColumns: string[] = ['id', 'name', 'description', 'price', 'categoryName', 'available', 'actions'];


  constructor(
    private productService: ProductService,
    private authService: AuthService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar
  ) {
    this.products$ = this.productService.findAll()
      .pipe(
        catchError(
          error => {
            console.log(error);
            authService.refreshToken();
            this.onError('Error loading products');
            return of([]);
          }
        )
      );
  }

  refresh() {
    this.products$ = this.productService.findAll()
      .pipe(
        catchError(
          error => {
            this.onError('Error loading products');
            return of([]);
          }
        )
      );
  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onDelete(product: Product) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Are you sure you want to remove this product?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
      this.productService.remove(product.id).subscribe(
        () => {
          this.refresh();
          this._snackBar.open('Product removed!', 'X', {
            duration: 5000,
            verticalPosition: 'bottom',
            horizontalPosition: 'center'
          });
        },
        () => {
          this.onError('Error removing product')
          this.authService.refreshToken();
        }
      )
    }
    });

  }

  onEdit(product: Product) {
    this.router.navigate(['edit/' + product.id], { relativeTo: this.route });
  }

  onError(errorMessage: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMessage,
    });
  }

  ngOnInit(): void {

  }

}
