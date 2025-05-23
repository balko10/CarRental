/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { HttpHeaders }                                       from '@angular/common/http';

import { Observable }                                        from 'rxjs';

import { CarDto } from '../models/models';


import { Configuration }                                     from '../configuration';



export interface CarControllerServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;

    /**
     *
     *
     * @param carDto
     */
    createCar(carDto: CarDto, extraHttpRequestParams?: any): Observable<{}>;

    /**
     *
     *
     * @param id
     */
    deleteCar(id: number, extraHttpRequestParams?: any): Observable<{}>;

    /**
     *
     *
     * @param id
     */
    getCar(id: number, extraHttpRequestParams?: any): Observable<CarDto>;

    /**
     *
     *
     */
    getCars(extraHttpRequestParams?: any): Observable<Array<CarDto>>;

    /**
     *
     *
     * @param id
     * @param carDto
     */
    updateCar(id: number, carDto: CarDto, extraHttpRequestParams?: any): Observable<{}>;

}
