import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import img1 from '../../../../assets/img/MainLogo.png';
import img2 from '../../../../assets/img/home.png';


// Externals
import classNames from 'classnames';
import PropTypes from 'prop-types';

// Material helpers
import { withStyles } from '@material-ui/core';

// Material components
import {
  Avatar,
  Divider,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  ListSubheader,
  Typography
} from '@material-ui/core';

// Material icons
import {
  DashboardOutlined as DashboardIcon,
  PeopleOutlined as PeopleIcon,
  ErrorOutline as ErrorIcon,
  ContactSupport as ContactIcon,
  StarRate as StarIcon,
  RecordVoiceOverOutlined as RecordIcon,
  ContactMail as MailIcon
} from '@material-ui/icons';

// Component styles
import styles from './styles';

class Sidebar extends Component {
  render() {
    const { classes, className } = this.props;

    const rootClassName = classNames(classes.root, className);

    return (
      <nav className={rootClassName}>
        <div className={classes.logoWrapper}>
          <Link className={classes.logoLink} to="/">            
            <img
              // className={classes.logoImage}
              className='img2' src={img2} alt='이미지가 없을때 뜨는 메세지' style={{width:'70px', height:'25px'}}/>
          </Link>
        </div>
        <Divider className={classes.logoDivider} />
        <div className={classes.profile} style={{height: "160px"}}>
          
          <img              
              // className={classes.logoImage}
              className='img1' src={img1} alt='이미지가 없을때 뜨는 메세지' style={{width:'120px', height:'120px', margin: 'auto'}}/>
         
        </div>
        <Divider className={classes.profileDivider} />
        <List component="div" disablePadding>
          <ListItem
            activeClassName={classes.activeListItem}
            className={classes.listItem}
            component={NavLink}
            to="/dashboard">
            <ListItemIcon className={classes.listItemIcon}>
              <DashboardIcon />
            </ListItemIcon>
            <ListItemText
              classes={{ primary: classes.listItemText }}
              primary="Dashboard"
            />
          </ListItem>
          <ListItem
            activeClassName={classes.activeListItem}
            className={classes.listItem}
            component={NavLink}
            to="/users">
            <ListItemIcon className={classes.listItemIcon}>
              <PeopleIcon />
            </ListItemIcon>
            <ListItemText
              classes={{ primary: classes.listItemText }}
              primary="유저"
            />
          </ListItem>
          <ListItem
            activeClassName={classes.activeListItem}
            className={classes.listItem}
            component={NavLink}
            to="/picklist">
            <ListItemIcon className={classes.listItemIcon}>
              <StarIcon />
            </ListItemIcon>
            <ListItemText
              classes={{ primary: classes.listItemText }}
              primary="픽스타"
            />
          </ListItem>
          <ListItem
            activeClassName={classes.activeListItem}
            className={classes.listItem}
            component={NavLink}
            to="/questionlist">
            <ListItemIcon className={classes.listItemIcon}>
              <MailIcon />
            </ListItemIcon>
            <ListItemText
              classes={{ primary: classes.listItemText }}
              primary="문의사항"
            />
          </ListItem>
          <ListItem
            activeClassName={classes.activeListItem}
            className={classes.listItem}
            component={NavLink}
            to="/noticelist">
            <ListItemIcon className={classes.listItemIcon}>
              <RecordIcon />
            </ListItemIcon>
            <ListItemText
              classes={{ primary: classes.listItemText }}
              primary="공지사항"
            />
          </ListItem>
          <ListItem
            activeClassName={classes.activeListItem}
            className={classes.listItem}
            component={NavLink}
            to="/faqslist">
            <ListItemIcon className={classes.listItemIcon}>
              <ContactIcon />
            </ListItemIcon>
            <ListItemText
              classes={{ primary: classes.listItemText }}
              primary="FAQs"
            />
          </ListItem>
          <ListItem
            activeClassName={classes.activeListItem}
            className={classes.listItem}
            component={NavLink}
            to="/police">
            <ListItemIcon className={classes.listItemIcon}>
              <ErrorIcon />
            </ListItemIcon>
            <ListItemText
              classes={{ primary: classes.listItemText }}
              primary="신고함"
            />
          </ListItem>
        </List>
      </nav>
    );
  }
}

Sidebar.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Sidebar);
