import React, { Component } from "react";

class NewReimbursementComponent extends Component {
  state = {
    addingNewReimbursement: false,
    amountValue: "",
    descriptionValue: "",
    typeValue: "LODGING",
    typeId: 0
  };

  handleChangeAmount = event => {
    this.setState({
      ...this.state,
      amountValue: event.target.value
    });
  };

  handleChangeDescription = event => {
    this.setState({
      ...this.state,
      descriptionValue: event.target.value
    });
  };

  handleChangeType = event => {
    this.setState({
      ...this.state,
      typeValue: event.target.value
    });
  };

  handleSubmit = event => {
    event.preventDefault();
    fetch("http://localhost:8080/ers/reimbursements", {
      method: "POST",
      body: JSON.stringify({
        amount: this.state.amountValue,
        description: this.state.descriptionValue,
        type: this.state.typeValue.toUpperCase()
      }),
      headers: {
        "Content-Type": "application/json"
      },
      credentials: "include"
    })
      .then(res => {
        if (res.status === 200) {
          this.props.reimbursementsUpdate();
        }
        return res.json();
      })
      .catch(err => {
        console.log(err);
      });
    this.setState({
      ...this.state,
      addingNewReimbursement: false,
      amountValue: "",
      descriptionValue: "",
      typeValue: "LODGING"
    });
  };

  render() {
    return (
      <>
        {this.state.addingNewReimbursement ? (
          <form onSubmit={this.handleSubmit}>
            <div className="form-group">
              <label htmlFor="amount" className="col col-form-label">
                Amount
              </label>
              <div className="col-sm-3">
                <input
                  type="text"
                  className="form-control"
                  id="amount"
                  placeholder="Amount"
                  pattern="[0-9]{1,20}"
                  required
                  onChange={this.handleChangeAmount}
                  value={this.state.amountValue}
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="description" className="col col-form-label">
                Description
              </label>
              <div className="col-sm-3">
                <textarea
                  className="text-area text-box multi-line"
                  data-val="true"
                  data-val-length="Maximum = 250 characters"
                  data-val-length-max="250"
                  id="description"
                  name="description"
                  cols="40"
                  rows="3"
                  required
                  onChange={this.handleChangeDescription}
                  value={this.state.descriptionValue}
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="inputState" className="col col-form-label">
                Type
              </label>
              <div className="col-sm-3">
                <select
                  id="inputState"
                  className="form-control"
                  onChange={this.handleChangeType}
                  value={this.state.typeValue}
                >
                  {/* <option selected>Choose...</option> */}
                  <option>Lodging</option>
                  <option>Travel</option>
                  <option>Food</option>
                  <option>Other</option>
                </select>
              </div>
            </div>

            <button type="submit" className="btn btn-primary">
              Submit
            </button>
          </form>
        ) : (
          <button
            className="btn btn-primary"
            type="button"
            onClick={this.addReimbursementTrue}
          >
            Create New Request
          </button>
        )}
      </>
    );
  }

  // true when adding a reimbursement,use false when you want to display the button
  addReimbursementTrue = () => {
    this.setState({
      ...this.state,
      addingNewReimbursement: true
    });
  };

  addReimbursementFalse = () => {
    this.setState({
      ...this.state,
      addingNewReimbursement: false
    });
  };
}

export default NewReimbursementComponent;
