import { Component, OnInit } from '@angular/core';
const mockTableData = require('../assets/mocks/mock-table-data.json');

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss',
    '../assets/styles/ag-grid.css',
    '../assets/styles/ag-theme-balham.css']
})
export class AppComponent implements OnInit {
  pageSize = 5;
  offsite = 0;
  rowData: any;
  totalRecords = mockTableData.length;
  selectedAccount: string = '';
  clickedDropdown: boolean = false;
  dropdownOptions: any[] = [];
  constructor() { }
  columnDefs = [
    { headerName: 'Transaction Reference', field: 'transactionReferenceNumber' },
    { headerName: 'Value Date', field: 'valueDate' },
    { headerName: 'Currency', field: 'paymentCurrency' },
    { headerName: 'Amount', field: 'paymentAmount' },
    { headerName: 'Beneficiary Name', field: 'beneficairyName' },
    { headerName: 'Account Name', field: 'accountName' }
  ];

  ngOnInit() {
    this.loadRowData();
    this.dropdownOptions = [...new Set(mockTableData.map((item: any) => item.transactionReferenceNumber))];
  }
  // call renderDropdown to show dropdownTemplate
  renderDropdown() {
    this.clickedDropdown = !this.clickedDropdown;
  }


  // load rowdata from mock data
  // page size is 5
  loadRowData(filter?: any) {
    if (filter) {
      this.rowData = mockTableData.filter((item: any) => item.transactionReferenceNumber === filter);
    } else {
      this.rowData = mockTableData.slice(this.offsite, this.offsite + this.pageSize);
    }
  }

  pageChanged(event: any) {
    this.offsite = (Number(event.target.text) - 1) * this.pageSize;
    this.loadRowData();
  }
  onSelectedAccount(event: any) {
    this.selectedAccount = event;
    this.loadRowData(this.selectedAccount);
    this.clickedDropdown = false;
  }
}
