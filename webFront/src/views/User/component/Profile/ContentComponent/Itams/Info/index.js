import React, { useState, useEffect, useCallback } from 'react';
import Axios from 'axios';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow
} from '@material-ui/core';

//직업, 매력 , 취미, 이상형 (3개씩 )

const Info = ({ userid }) => {
  const [info, setInfo] = useState({
    mytype: [],
    hobby: [],
    liketype: []
  });

  const Liketype = () => {
    const [state, setState] = useState([]);
    useEffect(() => {
      Axios.get(
        `http://sungjin5891.cafe24.com/user/list_liketype/${userid}`
      ).then(data => setState(data.data));
    }, []);

    return state;
  };

  const Mytype = () => {
    const [state, setState] = useState([]);
    useEffect(() => {
      Axios.get(
        `http://sungjin5891.cafe24.com/user/list_mytype/${userid}`
      ).then(data => setState(data.data));
    }, []);

    return state;
  };

  const Hobby = () => {
    const [state, setState] = useState([]);
    useEffect(() => {
      Axios.get(
        `http://sungjin5891.cafe24.com/user/list_userhobby/${userid}`
      ).then(data => setState(data.data));
    }, []);

    return state;
  };

  return (
    <React.Fragment>
      <Table>
        <TableHead />
        <TableBody>
          <TableRow hover>
            <TableCell align="left">
              <b style={{ fontSize: '1.3rem' }}>매력</b>
            </TableCell>
            {Mytype() !== undefined
              ? Mytype().map(mytype => (
                  <TableCell
                    style={{ fontSize: '1rem' }}
                    key={mytype.mno}
                    align="left">
                    {mytype.content}
                  </TableCell>
                ))
              : ''}
          </TableRow>
        </TableBody>
      </Table>
      <Table>
        <TableHead />
        <TableBody>
          <TableRow hover>
            <TableCell align="left">
              <b style={{ fontSize: '1.3rem' }}>이상형</b>
            </TableCell>
            {Liketype() !== undefined
              ? Liketype().map(liketype => (
                  <TableCell
                    style={{ fontSize: '1rem' }}
                    align="left"
                    key={liketype.lno}>
                    {liketype.content}
                  </TableCell>
                ))
              : ''}
          </TableRow>
        </TableBody>
      </Table>
      <Table>
        <TableHead />
        <TableBody>
          <TableRow hover>
            <TableCell align="left">
              <b style={{ fontSize: '1.3rem' }}>취미</b>
            </TableCell>
            {Hobby() !== undefined
              ? Hobby().map(hobby => (
                  <TableCell
                    style={{ fontSize: '1rem' }}
                    key={hobby.hno}
                    align="left">
                    {hobby.content}
                  </TableCell>
                ))
              : ''}
          </TableRow>
        </TableBody>
      </Table>
    </React.Fragment>
  );
};

export default Info;
