import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContent from '@material-ui/core/DialogContent';
import DialogActions from '@material-ui/core/DialogActions';
import Dialog from '@material-ui/core/Dialog';
import Axios from 'axios';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const useStyles = makeStyles(theme => ({
  root: {
    width: '100%'
  },
  heading: {
    fontSize: theme.typography.pxToRem(15),
    flexBasis: '33.33%',
    flexShrink: 0
  },
  secondaryHeading: {
    fontSize: theme.typography.pxToRem(15),
    color: theme.palette.text.secondary
  }
}));

export default function ConfirmationDialogRaw({
  onClose,
  value: valueProp,
  open,
  bno,
  ...other
}) {
  const [value, setValue] = React.useState(valueProp);
  const [comment, setComment] = React.useState([]);
  const radioGroupRef = React.useRef(null);
  const [expanded, setExpanded] = React.useState(false);

  const handleChange = panel => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };

  React.useEffect(() => {
    if (!open) {
      setValue(valueProp);
    }
    Axios.get(`http://sungjin5891.cafe24.com/board/reply/list/${bno}`).then(
      data => setComment(data.data)
    );
  }, [valueProp, open]);

  function handleEntering() {
    if (radioGroupRef.current != null) {
      radioGroupRef.current.focus();
    }
  }

  function handleCancel() {
    onClose();
  }

  function handleOk() {
    onClose(value);
  }
  return (
    <Dialog
      disableBackdropClick
      disableEscapeKeyDown
      maxWidth="xs"
      onEntering={handleEntering}
      aria-labelledby="confirmation-dialog-title"
      open={open}
      {...other}>
      <DialogTitle id="confirmation-dialog-title">댓글</DialogTitle>
      <DialogContent dividers>
        {comment.length != 0
          ? comment.map(data => (
              <ExpansionPanel
                expanded={expanded === 'panel1'}
                onChange={handleChange('panel1')}
                key={data.rno}>
                <ExpansionPanelSummary
                  expandIcon={<ExpandMoreIcon />}
                  aria-controls="panel1bh-content"
                  id={data.rno}>
                  <Typography style={{ flexBasis: '66.66%' }}>
                    {data.writer}
                  </Typography>
                  <Typography>{data.regdate}</Typography>
                </ExpansionPanelSummary>
                <ExpansionPanelDetails>
                  <Typography>{data.content}</Typography>
                </ExpansionPanelDetails>
              </ExpansionPanel>
            ))
          : '댓글이 존재하지 않습니다.'}
      </DialogContent>
      <DialogActions>
        <Button onClick={handleCancel} color="primary">
          Cancel
        </Button>
        <Button onClick={handleOk} color="primary">
          Ok
        </Button>
      </DialogActions>
    </Dialog>
  );
}
