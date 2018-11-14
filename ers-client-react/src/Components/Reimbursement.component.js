import React, { PureComponent } from 'react';

class ReimbursementComponent extends PureComponent {
    

    render() {
        const { reimbursementId, amount, submitted, resolved, description, author, resolver, status, type } = this.props.reimbursement;

        let determineColor = () => {
            if (status.status === 'PENDING') {
                return 'table-warning';
            } else if (status.status === 'APPROVED') {
                return 'table-success';
            } else if (status.status === 'DENIED') {
                return 'table-danger';
            }
        };

        return (
            <tr className={determineColor()}>
                <th scope="row">{reimbursementId}</th>
                <td>{amount}</td>
                <td>{submitted}</td>
                <td>{resolved}</td>
                <td>{description}</td>
                <td>{author}</td>
                <td>{resolver}</td>
                <td>{status.status}</td>
                <td>{type.type}</td>
            </tr>
        );
    }
}

export default ReimbursementComponent;