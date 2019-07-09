import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import clsx from 'clsx';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Collapse from '@material-ui/core/Collapse';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import { red } from '@material-ui/core/colors';
import FavoriteIcon from '@material-ui/icons/Favorite';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ChatBubbleOutline from '@material-ui/icons/ChatBubbleOutline';
import Button from '@material-ui/core/Button';
import Badge from '@material-ui/core/Badge';
import axios from 'axios';

const useStyles = makeStyles(theme => ({
  card: {
    maxWidth: 345
  },
  media: {
    height: 0,
    paddingTop: '56.25%' // 16:9
  },
  expand: {
    transform: 'rotate(0deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
      duration: theme.transitions.duration.shortest
    })
  },
  expandOpen: {
    transform: 'rotate(180deg)'
  },
  avatar: {
    backgroundColor: red[500]
  }
}));

const Cards = ({ title, content, writer, tag, date, id, userid, img }) => {
  const classes = useStyles();
  const [expanded, setExpanded] = React.useState(false);

  const [rcount, setRcount] = React.useState(0);
  const [lcount, setLcount] = React.useState(0);
  const [simg, setSImg] = React.useState('');

  React.useEffect(() => {
    axios
      .get(`http://192.168.0.104:5000/reply/count/${id}`)
      .then(data => setRcount(data.data));

    axios
      .get(`http://192.168.0.104:5000/board/bbslikecount/${id}`)
      .then(data => setLcount(data.data));

    axios
      .get(`http://192.168.0.26/user/read/${userid}`)
      .then(data => setSImg(data.data.soloimg));
  }, [id]);

  function handleExpandClick() {
    setExpanded(!expanded);
  }

  return (
    <Card className={classes.card} style={{ margin: 'auto' }}>
      <CardHeader
        avatar={
          <Avatar aria-label="Recipe" className={classes.avatar} src={simg}>
            <img src={simg} alt="프사" />
          </Avatar>
        }
        title={title}
        subheader={`${writer} / ${date}`}
      />
      <CardMedia className={classes.media} image={img} title="Paella dish" />
      <CardContent>
        <Typography variant="body2" color="textSecondary" component="p">
          {tag.length !== 0 ? (
            tag.map(data => <Button color="secondary">#{data}</Button>)
          ) : (
            <br />
          )}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="Add to favorites">
          <Badge
            color="primary"
            badgeContent={lcount}
            max={10000}
            showZero
            className={classes.margin}>
            <FavoriteIcon />
          </Badge>
        </IconButton>
        <IconButton aria-label="Share">
          <Badge
            color="primary"
            badgeContent={rcount}
            max={10000}
            showZero
            className={classes.margin}>
            <ChatBubbleOutline />
          </Badge>
        </IconButton>
        <IconButton
          className={clsx(classes.expand, {
            [classes.expandOpen]: expanded
          })}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="Show more">
          <ExpandMoreIcon />
        </IconButton>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <Typography paragraph>{content}</Typography>
        </CardContent>
      </Collapse>
    </Card>
  );
};

export default Cards;
