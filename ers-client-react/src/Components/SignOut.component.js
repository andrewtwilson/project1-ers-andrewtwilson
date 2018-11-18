import React, { Component } from 'react';

class SignOutComponent extends Component {
    state = {  }

    componentDidMount() {
        fetch('http://localhost:8080/ers/users/logout', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            credentials: 'include'
          })
            .then(res => {
              if (res.status === 200) {
                this.props.signOut();
                sessionStorage.clear();
                this.props.history.push('/home');
              }
            })
            .catch(err => {
              console.log(err);
            })
    }

    render() { 
        return ( 
            <div>Signing Out</div>
         );
    }
}
 
export default SignOutComponent;