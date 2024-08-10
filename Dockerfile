FROM mcr.microsoft.com/playwright:v1.35.1-focal
WORKDIR /app
COPY . .
RUN npm install
RUN npx playwright install-deps
ENTRYPOINT ["npx", "playwright", "test"]
