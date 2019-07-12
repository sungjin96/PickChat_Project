import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import { FAQs, Notice, Question } from '../Items';

const useStyles = makeStyles({
  root: {
    flexGrow: 1
  }
});

function TabContainer(props) {
  return (
    <Typography component="div" style={{ padding: 8 * 3 }}>
      {props.children === 'FAQs' ? <FAQs /> : ''}
      {props.children === 'Notice' ? <Notice /> : ''}
      {props.children === 'Question' ? <Question /> : ''}
    </Typography>
  );
}

const Tap = () => {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  function handleChange(event, newValue) {
    setValue(newValue);
  }

  return (
    <Paper
      className={classes.root}
      style={{ height: '100%', width: '90%', margin: 'auto' }}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
        centered>
        <Tab label="FAQ" style={{ fontSize: '1.3rem' }} />
        <Tab label="공지사항" style={{ fontSize: '1.3rem' }} />
        <Tab label="문의하기" style={{ fontSize: '1.3rem' }} />
      </Tabs>

      {value === 0 && <TabContainer>FAQs</TabContainer>}
      {value === 1 && <TabContainer>Notice</TabContainer>}
      {value === 2 && <TabContainer>Question</TabContainer>}
    </Paper>
  );
};

export default Tap;
