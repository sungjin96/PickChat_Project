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

  React.useEffect(() => {
    Axios.get(
      `http://sungjin5891.cafe24.com/user/list_blockuser/${userid}`
    ).then(data => setBlock(data.data));
  }, []);
  return (
    <Table>
      <TableHead>
        <TableRow>
          <TableCell align="left">
            <b>차단 한 아이디</b>
          </TableCell>
          <TableCell align="left">
            <b>차단 한 이름</b>
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
  );
};

export default BlockList;
