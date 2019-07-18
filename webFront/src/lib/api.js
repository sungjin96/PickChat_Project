import axios from 'axios';
import queryString from 'query-string';

export const NoticeInsert = ({ title, content }) =>
  axios.post('http://192.168.0.104:5000/notice/insert', { title, content });
