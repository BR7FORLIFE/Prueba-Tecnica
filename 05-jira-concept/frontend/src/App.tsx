import { RouterProvider } from "react-router";
import { ROUTES } from "./routes/routes";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

export default function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={ROUTES} />
    </QueryClientProvider>
  );
}
