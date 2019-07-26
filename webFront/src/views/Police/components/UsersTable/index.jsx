import React from 'react';
import { Link } from 'react-router-dom';
import CreateModal from '../createModal';
import classNames from 'classnames';
import PerfectScrollbar from 'react-perfect-scrollbar';
import { withStyles, Button } from '@material-ui/core';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
  Modal
} from '@material-ui/core';
import DeleteBtn from '../../../../containers/NoticeModalContainer/DeleteBtn';
import { Portlet, PortletContent } from 'components';
import styles from './styles';
import Axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';

const UsersTable = ({ classes, className }) => {
  const [modalState] = useSelector(
    state => [state.PoliceModule.modalState],
    []
  );
  const [state, setState] = React.useState({
    selectedUsers: [],
    rowsPerPage: 10,
    page: 0,
    client: [],
    setOpen: modalState
  });

  const dispatch = useDispatch();

  React.useEffect(() => {
    Axios.get('http://sungjin5891.cafe24.com/board/police/list').then(data =>
      setState({ client: data.data })
    );
  });

  const handleClose = () => {
    setState({ setOpen: false });
  };

  const rootClassName = classNames(classes.root, className);
  return (
    <Portlet className={rootClassName}>
      <PortletContent noPadding>
        <PerfectScrollbar>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell
                  align="left"
                  style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
                  번호
                </TableCell>
                <TableCell
                  align="left"
                  style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
                  신고자
                </TableCell>
                <TableCell
                  align="left"
                  style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
                  신고 글
                </TableCell>
                <TableCell
                  align="left"
                  style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
                  신고 내용
                </TableCell>
                <TableCell
                  align="left"
                  style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
                  신고 유형
                </TableCell>
                <TableCell
                  align="left"
                  style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
                  해당글 삭제
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {state.client !== undefined
                ? state.client.map(user => (
                    <TableRow
                      className={classes.tableRow}
                      hover
                      key={user.bpno}>
                      <TableCell className={classes.tableCell}>
                        <div className={classes.tableCellInner}>
                          <Typography
                            className={classes.nameText}
                            variant="body1"
                            style={{ fontSize: '1.1rem' }}>
                            {user.bpno}
                          </Typography>
                        </div>
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
                        {user.sender}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}
                        onClick={() =>
                          dispatch({
                            type: 'Police/MODAL_CHECK',
                            payload: {
                              bnoo: user.bno
                            }
                          })
                        }>
                        {user.bno}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
                        {user.pcontent}
                      </TableCell>

                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
                        {user.rpcontent}
                      </TableCell>
                      <TableCell
                        className={classes.tableCell}
                        style={{ fontSize: '1.1rem' }}>
                        {user.bno === 0 ? (
                          <Button
                            onClick={() =>
                              dispatch({
                                type: 'Police/REMOVE',
                                payload: {
                                  no: user.bpno
                                }
                              })
                            }>
                            삭제됨
                          </Button>
                        ) : (
                          <Button
                            onClick={() =>
                              dispatch({
                                type: 'Police/DELETE',
                                payload: {
                                  bno: user.bno,
                                  bpno: user.bpno
                                }
                              })
                            }>
                            DELETE
                          </Button>
                        )}
                      </TableCell>
                    </TableRow>
                  ))
                : ''}
            </TableBody>
          </Table>
        </PerfectScrollbar>
      </PortletContent>
      <Modal
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
        open={modalState}
        onClose={() =>
          dispatch({
            type: 'Police/MODAL_CHECK1'
          })
        }>
        <div style={{}}>
          <CreateModal />
        </div>
      </Modal>
    </Portlet>
  );
};

export default withStyles(styles)(UsersTable);
