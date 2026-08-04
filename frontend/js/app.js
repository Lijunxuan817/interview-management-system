const App = {
  getParam(n) { return new URLSearchParams(location.search).get(n); },
  openPositions() { return MOCK.positions.filter(p => p.status === "open"); },
  getPosition(id) { return MOCK.positions.find(p => String(p.id) === String(id)); },
};
