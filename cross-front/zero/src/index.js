import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Store from "./reducers/store";

ReactDOM.render(
    <Store>
        <App/>
    </Store>,
    document.getElementById('root')
);

reportWebVitals()
