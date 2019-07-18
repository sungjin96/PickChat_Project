import React from 'react';
import Axios from 'axios';
import {
  Avatar,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography
} from '@material-ui/core';

const BlockList = ({ userid }) => {
  const [block, setBlock] = React.useState([]);
  const [blocked, setBlocked] = React.useState([]);

  React.useEffect(() => {
    Axios.get(
      `http://sungjin5891.cafe24.com/user/list_blockuser/${userid}`
    ).then(data => setBlock(data.data));

    Axios.get(
      `http://sungjin5891.cafe24.com/user/list_blockeduser/${userid}`
    ).then(data => setBlocked(data.data));
  }, []);

  console.log(block);
  return (
    <React.Fragment>
      {block.length === 0 && blocked.length === 0 ? (
        '데이터가 없습니다.'
      ) : (
        <React.Fragment>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left">
                  <b>내가 차단 한 아이디</b>
                </TableCell>
                <TableCell align="left">
                  <b>내가 차단 한 이름</b>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {block !== undefined
                ? block.map(user => (
                    <TableRow hover key={user.blockno}>
                      <TableCell style={{ fontSize: '1rem' }}>
                        {user.blocked}
                      </TableCell>
                      <TableCell style={{ fontSize: '1rem' }}>
                        {user.blockedname}
                      </TableCell>
                    </TableRow>
                  ))
                : ''}
            </TableBody>
          </Table>
          <hr />
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left">
                  <b>나를 차단 한 아이디</b>
                </TableCell>
                <TableCell align="left">
                  <b>나를 차단 한 이름</b>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {blocked !== undefined
                ? blocked.map(user => (
                    <TableRow hover key={user.blockno}>
                      <TableCell style={{ fontSize: '1rem' }}>
                        {user.blocker}
                      </TableCell>
                      <TableCell style={{ fontSize: '1rem' }}>
                        {user.blockedname}
                      </TableCell>
                    </TableRow>
                  ))
                : ''}
            </TableBody>
          </Table>
        </React.Fragment>
      )}
    </React.Fragment>
  );
};

export default BlockList;
