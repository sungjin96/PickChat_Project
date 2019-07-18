import React from 'react';
import { Link } from 'react-router-dom';
import classNames from 'classnames';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { withStyles, Button } from '@material-ui/core';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography
} from '@material-ui/core';
import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';
import { Portlet, PortletContent } from 'components';
import styles from './styles';
import Axios from 'axios';
import {useDispatch} from 'react-redux'

const UsersTable = ({ classes, className }) => {
  const [state, setState] = React.useState({
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: []
<<<<<<< HEAD
  })
  const dispatch = useDispatch();
  React.useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/board/police/list').then(data =>
    setState({...state, client: data.data})
  );
  }, [state.client])

  const rootClassName = classNames(classes.root, className);

  return (
    <Portlet className={rootClassName}>
      <PortletContent noPadding>
        <PerfectScrollbar>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="left">번호</TableCell>
                <TableCell align="left">신고자</TableCell>
                <TableCell align="left">신고 글</TableCell>
                <TableCell align="left">신고 내용</TableCell>
                <TableCell align="left">신고 유형</TableCell>
                <TableCell align="left">삭제</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client.map(user => (
                    <TableRow
                      className={classes.tableRow}
                      hover
                      key={user.bpno}
                    >
                      <TableCell className={classes.tableCell}>
                        <div className={classes.tableCellInner}>
                          <Link to="#">
                            <Typography
                              className={classes.nameText}
                              variant="body1"
                              style={{ fontSize: '1rem' }}>
                              {user.bpno}
                            </Typography>
                          </Link>
                        </div>
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.sender}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.bno}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.rpcontent}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1rem' }}>
                        {user.reasonid}
                      </TableCell>
                      <TableCell
                      className={classes.tableCell}
                      style={{ fontSize: '1rem' }}>
                        <Button onClick={() => dispatch({type:'Police/DELETE', payload: {
                          bpno: user.bpno
                        }})}>DELETE</Button>
                    </TableCell>
                    </TableRow>
                  ))
                : ''}
            </TableBody>
          </Table>
        </PerfectScrollbar>
      </PortletContent>
    </Portlet>
  );
=======
  };

  componentDidMount() {
    Axios.get('http://sungjin5891.cafe24.com/boardpolice/list').then(data =>
      this.setState((this.state.client = data.data))
    );
  }

  handleSelectAll = event => {
    const { users, onSelect } = this.props;

    let selectedUsers;

    if (event.target.checked) {
      selectedUsers = users.map(user => user.userid);
    } else {
      selectedUsers = [];
    }

    this.setState({ selectedUsers });

    onSelect(selectedUsers);
  };

  handleSelectOne = (event, id) => {
    const { onSelect } = this.props;
    const { selectedUsers } = this.state;

    const selectedIndex = selectedUsers.indexOf(id);
    let newSelectedUsers = [];

    if (selectedIndex === -1) {
      newSelectedUsers = newSelectedUsers.concat(selectedUsers, id);
    } else if (selectedIndex === 0) {
      newSelectedUsers = newSelectedUsers.concat(selectedUsers.slice(1));
    } else if (selectedIndex === selectedUsers.length - 1) {
      newSelectedUsers = newSelectedUsers.concat(selectedUsers.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelectedUsers = newSelectedUsers.concat(
        selectedUsers.slice(0, selectedIndex),
        selectedUsers.slice(selectedIndex + 1)
      );
    }

    this.setState({ selectedUsers: newSelectedUsers });

    onSelect(newSelectedUsers);
  };

  handleChangePage = (event, page) => {
    this.setState({ page });
  };

  handleChangeRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value });
  };

  render() {
    const { classes, className, users } = this.props;
    const { selectedUsers, page, client } = this.state;

    const rootClassName = classNames(classes.root, className);
    return (
      <Portlet className={rootClassName}>
        <PortletContent noPadding>
          <PerfectScrollbar>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                    번호
                  </TableCell>
                  <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                    신고자
                  </TableCell>
                  <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                    신고 글
                  </TableCell>
                  <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                    신고 내용
                  </TableCell>
                  <TableCell align="left" style={{ fontSize: '1.1rem' }}>
                    reasonid
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {client !== undefined
                  ? client.map(user => (
                      <TableRow
                        className={classes.tableRow}
                        hover
                        key={user.bpno}
                        selected={selectedUsers.indexOf(user.bpno) !== -1}>
                        <TableCell className={classes.tableCell}>
                          <div className={classes.tableCellInner}>
                            <Link to="#">
                              <Typography
                                className={classes.nameText}
                                variant="body1"
                                style={{ fontSize: '1rem' }}>
                                {user.bpno}
                              </Typography>
                            </Link>
                          </div>
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.sender}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.bno}
                        </TableCell>

                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.pcontent}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.reasonid}
                        </TableCell>
                      </TableRow>
                    ))
                  : ''}
              </TableBody>
            </Table>
          </PerfectScrollbar>
        </PortletContent>
      </Portlet>
    );
  }
>>>>>>> f50f6d38b4f81ccaede0cee72cfbeebe8b97f799
}



export default withStyles(styles)(UsersTable);
