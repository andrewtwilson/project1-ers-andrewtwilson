import React, { Component } from "react";
import EmployeeSideBarComponent from "./EmployeeSideBar.component";
import ManagerSideBarComponent from "./ManagerSideBar.component";

class SideBarComponent extends Component {
  state = {};
  render() {
    return (
      <>
        {sessionStorage.getItem("userRole") === "FINANCEMAN" ? (
          <ManagerSideBarComponent />
        ) : (
          <EmployeeSideBarComponent />
        )}
      </>
    );
  }
}

export default SideBarComponent;
