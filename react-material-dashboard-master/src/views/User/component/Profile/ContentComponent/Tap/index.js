import React from 'react';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import * as Itams from '../Itams';

import './style.css';
import Axios from 'axios';

const AntTabs = withStyles({
  root: {
    borderBottom: '1px solid #e8e8e8'
  },
  indicator: {
    backgroundColor: '#1890ff'
  }
})(Tabs);

const AntTab = withStyles(theme => ({
  root: {
    textTransform: 'none',
    minWidth: 72,
    fontWeight: theme.typography.fontWeightRegular,
    marginRight: theme.spacing(4),
    fontFamily: [
      '-apple-system',
      'BlinkMacSystemFont',
      '"Segoe UI"',
      'Roboto',
      '"Helvetica Neue"',
      'Arial',
      'sans-serif',
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"'
    ].join(','),
    '&:hover': {
      color: '#40a9ff',
      opacity: 1
    },
    '&$selected': {
      color: '#1890ff',
      fontWeight: theme.typography.fontWeightMedium
    },
    '&:focus': {
      color: '#40a9ff'
    }
  },
  selected: {}
}))(props => <Tab disableRipple {...props} />);

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  typography: {
    padding: theme.spacing(3)
  },
  demo1: {
    backgroundColor: theme.palette.background.paper
  },
  demo2: {
    backgroundColor: '#2e1534'
  }
}));

const Tap = ({ id }) => {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);
  const [bbss, setBbss] = React.useState('');

  function handleChange(event, newValue) {
    setValue(newValue);
  }

  function TabContainer(props) {
    return (
      <Typography component="div" style={{ padding: 8 * 3 }}>
        {props.children === '게시글' ? <Itams.BoardList data={bbss} /> : ''}
        {props.children === '차단' ? <Itams.BlockList userid={id} /> : ''}
        {props.children === '정보' ? <Itams.BlockList data={bbss} /> : ''}
      </Typography>
    );
  }

  React.useEffect(() => {
    Axios.get(`http://sungjin5891.cafe24.com/board/userbbslist/${id}`).then(
      data => setBbss(data.data)
    );
  }, []);

  return (
    <div>
      <AntTabs
        value={value}
        onChange={handleChange}
        style={{ paddingLeft: '30%' }}>
        <AntTab label="본인 정보" style={{ margin: '0 1rem' }} />
        <AntTab label="게시글" style={{ margin: '0 1rem' }} />
        <AntTab label="차단" style={{ margin: '0 1rem' }} />
      </AntTabs>
      <Typography className={classes.typography} />
      {value === 0 && <TabContainer>정보</TabContainer>}
      {value === 1 && <TabContainer>게시글</TabContainer>}
      {value === 2 && <TabContainer>차단</TabContainer>}
    </div>
  );
};

export default Tap;
