import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppMaterialModule } from './shared/app-material/app-material.module';
import { HttpClientModule, provideHttpClient, withFetch, withInterceptors, withJsonpSupport } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AuthModule } from './auth/auth.module';
import { tokenInterceptor } from './interceptors/token.interceptor';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppMaterialModule,
    AuthModule
  ],
  providers: [
    provideAnimationsAsync(),
    provideHttpClient(
      withFetch(),
      withJsonpSupport(),
      withInterceptors( [tokenInterceptor] )
    )
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
