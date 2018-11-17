const dev = {
  ersContext: "http://localhost:8080/ers/"
};

const prod = {
  ersContext: "http://1810leagueapi-env.vn7qtqqrnd.us-west-2.elasticbeanstalk.com/ers/"
};

export const environment = process.env.NODE_ENV === "production" ? prod : dev;