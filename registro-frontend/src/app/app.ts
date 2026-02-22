import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, signal } from '@angular/core';
import { FormBuilder, NgForm, ReactiveFormsModule, UntypedFormGroup } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterOutlet } from '@angular/router';
import { Persona } from './model/persona';
import { Respuesta } from './model/respuesta';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,
  ReactiveFormsModule,CommonModule,
  HttpClientModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('registro-frontend');

  persona:Persona =new Persona();
  respuesta!:Respuesta;
form!:UntypedFormGroup;

  constructor(private formBuilder:FormBuilder,
    private httpClient:HttpClient
  ){
    this.form = this.crearFormulario();
  }

crearFormulario(){
  return this.formBuilder.group({
    documento: [''],
    nombre: [''],
    apellido: [''],
    email: ['']
  });
}

  enviar(){
console.log(this.form.value);

    this.persona.documento = this.form.value.documento;
    this.persona.nombre = this.form.value.nombre;
    this.persona.apellido = this.form.value.apellido;
    this.persona.email = this.form.value.email;
    console.log(this.persona);
    

    if(this.persona.documento.toString()==''){
      alert("El documento es obligatorio");
      return;
    }

    if(this.persona.nombre==''){
      alert("El nombre es obligatorio");
      return;
    }

    

    this.httpClient.post<Respuesta>("http://localhost:8081/registropersona-back/persona/guardar",this.persona).subscribe({
      next: dato =>{
        this.respuesta =dato;
          alert(this.respuesta.mensaje);
      }
    });

  }


}
