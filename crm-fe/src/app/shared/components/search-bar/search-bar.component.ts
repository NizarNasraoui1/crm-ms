import { Component, Input, OnInit, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SearchFields } from '../../models/searchFields';
import { SearchForm } from '../../models/searchForm';
import { SearchParams } from '../../models/searchParams';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit, OnChanges {
  @Input() searchParams: SearchParams;
  @Output() searchForm = new EventEmitter<SearchForm>();
  searchFormGroup: FormGroup;
  selectedCity: any;

  constructor() {
    this.initForm();
  }

  ngOnInit(): void {
    this.searchFormGroup.valueChanges.subscribe((form) => {
      this.searchForm.emit(this.transformFormToSearchForm(form));
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.searchParams){
      this.searchFormGroup.get('searchFields')?.setValue([this.searchParams.searchFields[0]])
    }
  }

  initForm() {
    this.searchFormGroup = new FormGroup({
      searchWord: new FormControl(null),
      searchFields: new FormControl(null),
      sortField: new FormControl(null),
      sortDirection: new FormControl(null)
    })
  }

  transformFormToSearchForm(form: any): SearchForm {
    const searchFields: string[] = [];
    if (form.searchFields) {
      form.searchFields.forEach((e: any) => {
        searchFields.push(e.name);
      });
    }
    const sortField = form.sortField ? form.sortField.name : null;
    return new SearchForm(form.searchWord, searchFields, sortField, form.sortDirection);
  }



}