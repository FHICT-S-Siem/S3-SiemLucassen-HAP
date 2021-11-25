import axios from 'axios';
const API_URL = process.env.REACT_APP_DEV_API_URL;
export default axios.create({
    baseURL: `http://localhost:4444/${API_URL}`
});
