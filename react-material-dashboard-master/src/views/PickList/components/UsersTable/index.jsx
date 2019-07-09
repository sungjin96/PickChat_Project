import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';

// Externals
import classNames from 'classnames';
import PropTypes from 'prop-types';
import moment from 'moment';
import PerfectScrollbar from 'react-perfect-scrollbar';

// Material helpers
import { withStyles, Button } from '@material-ui/core';

// Material components
import {
  Avatar,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
  TablePagination
} from '@material-ui/core';

// Shared helpers
import { getInitials } from 'helpers';

// Shared components
import { Portlet, PortletContent } from 'components';

// Component styles
import styles from './styles';
import Axios from 'axios';

class UsersTable extends Component {
  state = {
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: []
  };

  componentDidMount() {
    Axios.get('http://192.168.0.104:5000/board/bbslist').then(data =>
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
                  <TableCell align="left">작성자</TableCell>
                  <TableCell align="left">제목</TableCell>
                  <TableCell align="left">내용</TableCell>
                  <TableCell align="left">태그</TableCell>
                  <TableCell align="left">등록일</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {client !== undefined
                  ? client.map(user => (
                      <TableRow
                        className={classes.tableRow}
                        hover
                        key={user.bno}
                        selected={selectedUsers.indexOf(user.bno) !== -1}>
                        <TableCell className={classes.tableCell}>
                          <div className={classes.tableCellInner}>
                            <Avatar
                              className={classes.avatar}
                              src={user.imgpath}>
                              {getInitials(
                                user.writer !== null ? user.writer : ''
                              )}
                            </Avatar>
                            <Link to="#">
                              <Typography
                                className={classes.nameText}
                                variant="body1"
                                style={{ fontSize: '1rem' }}>
                                {user.writer}
                              </Typography>
                            </Link>
                          </div>
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.title}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.content}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {user.tagword.map((data, index) => (
                            <Chip
                              key={index}
                              label={'#' + data}
                              variant="outlined"
                            />
                          ))}
                        </TableCell>
                        <TableCell
                          className={classes.tableCell}
                          style={{ fontSize: '1rem' }}>
                          {moment(user.regdate).format('DD/MM/YYYY')}
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
}

UsersTable.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired,
  onSelect: PropTypes.func,
  onShowDetails: PropTypes.func,
  users: PropTypes.array.isRequired
};

UsersTable.defaultProps = {
  users: [],
  onSelect: () => {},
  onShowDetails: () => {}
};

export default withStyles(styles)(UsersTable);
