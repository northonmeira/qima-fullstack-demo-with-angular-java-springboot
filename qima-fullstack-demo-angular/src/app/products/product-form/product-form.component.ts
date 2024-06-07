import { Product } from './../model/product';
import { ProductService } from './../services/product.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { Observable, catchError, of } from 'rxjs';
import { Category } from '../model/gategory';
import { CategoryService } from '../services/category.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Route } from '@angular/router';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.css'
})
export class ProductFormComponent implements OnInit {

  product: Product;

  categorys: Category[];

  form: FormGroup;

  productAvailable = new FormControl(false);

  onSubmit() {
    if(this.product == undefined) {
      this.createProduct(this.form.value)
    } else {
      this.updateProduct(this.form.value);
    }
  }

  createProduct(request: any) {
    this.productService.save(request).subscribe({
      next: (result) => {
        this.location.back();
      },
      error: (e) => {
        console.log(e);
        this._snackBar.open('Error Saving Product, see console log', '', { duration: 1500 });
      }
    })
  }

  updateProduct(request: any) {
    var product: Product = request;
    product.id = this.product.id;
    this.productService.update(product).subscribe({
      next: (result) => {
        this.location.back();
      },
      error: (e) => {
        console.log(e);
        this._snackBar.open('Error Updating Product, see console log', '', { duration: 1500 });
      }
    })
  }

  onCancel() {
    this.location.back();
  }

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private categoryService: CategoryService,
    private _snackBar: MatSnackBar,
    public dialog: MatDialog,
    private location: Location,
    private route: ActivatedRoute,
  ) {
    this.categorys = [];
    this.categoryService.findAll()
    .pipe(
      catchError(
        error => {
          console.log(error);
          this.onError('Error loading categorys');
          return of([]);
        }
      )
    ).subscribe((categorys) => this.categorys = categorys);

    this.product = this.route.snapshot.data[0];

    if(this.product != undefined) {
      this.form = this.formBuilder.group({
        name: [this.product.name],
        description: [this.product.description],
        price: [this.product.price],
        categoryId: [this.product.categoryId],
        available: [this.product.available]
      });
    } else {
      this.form = this.formBuilder.group({
        name: [],
        description: [],
        price: [],
        categoryId: [],
        available: []
    });
  }
  }

  onError(errorMessage: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMessage,
    });
  }

  ngOnInit(): void {
    this.categorys = [];
    this.categoryService.findAll()
    .pipe(
      catchError(
        error => {
          console.log(error);
          this.onError('Error loading categorys');
          return of([]);
        }
      )
    ).subscribe((categorys) => this.categorys = categorys);
    const product: Product = this.route.snapshot.data[0];

    if(product != undefined) {
      this.form = this.formBuilder.group({
        name: [product.name],
        description: [product.description],
        price: [product.price],
        categoryId: [product.categoryId],
        available: [product.available]
      });
    } else {
      this.form = this.formBuilder.group({
        name: [],
        description: [],
        price: [],
        categoryId: [],
        available: []
    });
  }
  }

  isProductAvailable(): boolean {
    if(this.product == undefined)
      return false;
    return this.product.available;
  }

}
