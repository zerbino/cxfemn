# Manual for using Substitution in a cxf project
Substitution is a project raised by Mr. GRALL in the Nantes Engineer School to solve the problem of the contravention of the Liskov substitution principle. The project is carried out by Kevin Llopart, Raphael Martignoni, Gregoire Seguin-Henry and Hao Zhang.

## The purpose of the project Substitution
The Substitution project is raised to solve the problem of the contravention of the Liskov substitution principle in the framework cxf.
The framework cxf is contrary to the Liskov substitution principle in two aspects:

1. The coupling between the service layer and the object layer.
- The problem of the inter-operation of inheritance between the client and the server.

## How does it work?
It works with the support of interceptor and filter in cxf framework. We compare the structure of the body supplied by the sender and the structure needed by the receiver, prune away the redundant elements and modify the name of the body. After, the receiver may unmarshall the body smoothly.

## How to use it?


log:
- first version: Hao, 15 jan, 2014
without the principle and the usage.

- second version: Hao, 15 jan, 2014
add the principle.

