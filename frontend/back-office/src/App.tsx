import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router";
import AppLayout from "./layout/AppLayout";
import SignIn from "./pages/AuthPages/SignIn";
import NotFound from "./pages/OtherPage/NotFound";
import Home from "./pages/Dashboard/Home";
import Competitions from "./pages/Competitions";
import Events from "./pages/Events";
import Participants from "./pages/Participants";
import Results from "./pages/Results";
import Missions from "./pages/Missions";
import Notifications from "./pages/Notifications";
import Profile from "./pages/Profile";
import VolunteerPlanning from "./pages/VolunteerPlanning";
import Statistics from "./pages/Statistics";
import { ScrollToTop } from "./components/common/ScrollToTop";
import ProtectedRoute from "./components/auth/ProtectedRoute";

export default function App() {
  return (
    <Router>
      <ScrollToTop />
      <Routes>
        <Route path="/login" element={<SignIn />} />

        <Route
          element={
            <ProtectedRoute>
              <AppLayout />
            </ProtectedRoute>
          }
        >
          <Route path="/" element={<Home />} />
          <Route path="/competitions" element={<Competitions />} />
          <Route path="/events" element={<Events />} />
          <Route path="/participants" element={<Participants />} />
          <Route path="/results" element={<Results />} />
          <Route path="/missions" element={<Missions />} />
          <Route path="/notifications" element={<Notifications />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/volunteer-planning" element={<VolunteerPlanning />} />
          <Route path="/statistics" element={<Statistics />} />
        </Route>

        <Route path="/signin" element={<Navigate to="/login" replace />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}