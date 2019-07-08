import React, { Component } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

// Views
import Dashboard from './views/Dashboard';
import ProductList from './views/ProductList';
import UserList from './views/UserList';
import Typography from './views/Typography';
import Icons from './views/Icons';
import Account from './views/Account';
import Settings from './views/Settings';
import SignIn from './views/SignIn';
import UnderDevelopment from './views/UnderDevelopment';
import NotFound from './views/NotFound';
import Main from './views/Main';
import PickSta from './views/PickSta';
import Cs from './views/Cs';
import QuestionList from './views/QuestionList';
import PickList from 'views/PickList';
import NoticeList from 'views/NoticeList';
import FAQsList from 'views/FAQsList';

export default class Routes extends Component {
  render() {
    return (
      <Switch>
        <Route component={Main} exact path="/" />
        <Route component={FAQsList} exact path="/faqslist" />
        <Route component={NoticeList} exact path="/noticelist" />
        <Route component={QuestionList} exact path="/questionlist" />
        <Route component={PickList} exact path="/picklist" />
        <Route component={PickSta} exact path="/pick" />
        <Route component={Cs} exact path="/cs" />
        <Route component={Dashboard} exact path="/dashboard" />
        <Route component={UserList} exact path="/users" />
        <Route component={ProductList} exact path="/products" />
        <Route component={Typography} exact path="/typography" />
        <Route component={Icons} exact path="/icons" />
        <Route component={Account} exact path="/account" />
        <Route component={Settings} exact path="/settings" />
        <Route component={SignIn} exact path="/login" />
        <Route component={UnderDevelopment} exact path="/under-development" />
        <Route component={NotFound} exact path="/not-found" />
        <Redirect to="/not-found" />
      </Switch>
    );
  }
}
