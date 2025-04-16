const http = {
  baseUrl: "",
  get: function (url) {
    // console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url);
  },
  post: function (url, body) {
    // console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url, {
      method: "POST",
      body: body,
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    });
  },
  put: function (url, body) {
    // console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url, {
      method: "PUT",
      body: body,
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    });
  },
  delete: function (url, body) {
    // console.log(this.baseUrl + url);

    return fetch(this.baseUrl + url, {
      method: "DELETE",
      body: body,
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    });
  },
};

export default http;