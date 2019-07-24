import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
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

const Item = ({ title, content, comment, writer }) => {
  const classes = useStyles();
  const [expanded, setExpanded] = React.useState(false);

  const handleChange = panel => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };
  return (
    <ExpansionPanel
      expanded={expanded === 'panel1'}
      onChange={handleChange('panel1')}>
      <ExpansionPanelSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="panel1bh-content"
        id="panel1bh-header">
        <Typography className={classes.heading}>
          {writer === undefined ? title : writer}
        </Typography>
        <Typography className={classes.secondaryHeading}>
          {writer === undefined
            ? comment === undefined
              ? ''
              : content
            : title}
        </Typography>
      </ExpansionPanelSummary>
      <ExpansionPanelDetails style={{ width: '100%' }}>
        <Typography
          style={{ width: '100%', height: '100%', textAlign: 'center' }}>
          <div
            style={{
              backgroundColor: '#F3E8FF',
              paddingTop: '0.1rem',
              fontSize: '1.1rem'
            }}>
            {writer === undefined ? (
              ''
            ) : (
              <div
                style={{
                  fontSize: '1.5rem',
                  // width: '100%',
                  textAlign: 'left',
                  margin: '1rem',
                  width: '95%',
                  height: '95%',
                  backgroundColor: '#fff'
                }}
              />
            )}

            {writer === undefined ? '' : content}
          </div>
          <div
            style={{
              backgroundColor: '#f8f8f8',
              paddingTop: '0.1rem',
              paddingBottom: '2rem',
              marginTop: '0.5rem',
              fontSize: '1.1rem'
            }}>
            <div
              style={{
                fontSize: '1.5rem',
                width: '100%',
                textAlign: 'left',
                margin: '0.8rem'
              }}>
              Answer
            </div>
            {comment === undefined ? content : comment}
          </div>
        </Typography>
      </ExpansionPanelDetails>
    </ExpansionPanel>
  );
};

export default Item;
