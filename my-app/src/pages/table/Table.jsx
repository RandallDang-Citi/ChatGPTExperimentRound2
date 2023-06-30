import React from 'react';
import { Space, Table, Tag } from 'antd';

// implement table component with ant design
// https://ant.design/components/table/

const columns = [
    {
        title: 'Transaction Reference',
        dataIndex: 'transactionReferenceNumber',
        key: 'transactionReferenceNumber',
    },
    {
        title: 'Value Date',
        dataIndex: 'valueDate',
        key: 'valueDate',
        render: (text) => { return formatDate(text) },
    },
    {
        title: 'Currency',
        dataIndex: 'paymentCurrency',
        key: 'paymentCurrency',
    },
    {
        title: 'Amount',
        dataIndex: 'paymentAmount',
        key: 'paymentAmount',
    },
    {
        title: 'Beneficiary Name',
        dataIndex: 'beneficiaryName',
        key: 'beneficiaryName',
    },
    {
        title: 'Account Name',
        dataIndex: 'accountName',
        key: 'accountName',
    }
];

// format date to dd/mm/yyyy
const formatDate = (date) => {
    const d = new Date(date);
    return `${d.getDate()}/${d.getMonth() + 1}/${d.getFullYear()}`;
}

const PaymentTable = (props) => <Table columns={columns} dataSource={props && props.data} />;

export default PaymentTable;