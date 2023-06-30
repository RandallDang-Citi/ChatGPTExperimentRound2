import { useEffect, useState } from 'react';
import PaymentTable from '../table/Table';
import './home.css'
import txnList from '../../mock/txn';
import { Button, Dropdown, Space } from 'antd';
import { DownOutlined } from '@ant-design/icons';

const Home = () => {
    // load data from mock/txn.json
    const [data, setData] = useState([]);
    const [items, setItems] = useState([]);

    useEffect(() => {
        setData(txnList);

        // get account name list from data without duplication
        const accountNameList = [...new Set(txnList.map(txn => txn.accountName))];

        // assign values for items, key is account name, label is account name
        const items = accountNameList.map((accountName, index) => {
            return {
                key: accountName,
                label: accountName,
            }
        });

        setItems(items);
    }, []);


    const handleMenuClick = (e) => {
        // filter data by account name from txnList        
        const filteredData = txnList.filter(txn => txn.accountName === e.key);
        setData(filteredData);
    };

    // new dropdown component by ant design
    // https://ant.design/components/select/   

    const menuProps = {
        items,
        onClick: handleMenuClick,
    };

    return (
        <div className="home">
            <h1>Home</h1>
            <Dropdown menu={menuProps} className='filter'>
                <Button>
                    <Space>
                        <span>Filter by Account Name</span>
                        <DownOutlined />
                    </Space>
                </Button>
            </Dropdown>
            <PaymentTable data={data} className='table' />
        </div>
    );
}

export default Home;
