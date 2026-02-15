# Render Deployment Guide for AI Tool Summary Generator

## Overview
This guide will help you deploy your Spring Boot application on Render with all necessary environment variables configured.

## Prerequisites
- Render account
- Google Cloud Console account (for OAuth2)
- Gemini API key
- Database (MySQL/PostgreSQL)

## Step 1: Create a New Web Service on Render

1. Go to [Render Dashboard](https://dashboard.render.com/)
2. Click "New +" → "Web Service"
3. Connect your GitHub repository
4. Configure the service:
   - **Name**: `ai-tool-summary-generator` (or your preferred name)
   - **Environment**: `Java`
   - **Build Command**: `./mvnw clean install`
   - **Start Command**: `java -jar target/ai-tool-summary-generator-0.0.1-SNAPSHOT.jar`

## Step 2: Required Environment Variables

### Essential Variables (Required)

| Variable Name | Description | Example Value | Required |
|---------------|-------------|---------------|----------|
| `GEMINI_API_KEY` | Your Google Gemini API key | `AIzaSyC...` | ✅ Yes |
| `DATABASE_URL` | Database connection URL | `jdbc:mysql://...` | ✅ Yes |
| `DATABASE_USERNAME` | Database username | `myuser` | ✅ Yes |
| `DATABASE_PASSWORD` | Database password | `mypassword` | ✅ Yes |
| `JWT_SECRET` | Secret key for JWT tokens | `your-super-secret-key-here` | ✅ Yes |

### Database Configuration

**For MySQL (Recommended for Render):**
```
DATABASE_URL=jdbc:mysql://your-mysql-host:3306/your_database_name
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
DATABASE_DRIVER=com.mysql.cj.jdbc.Driver
```

**For PostgreSQL:**
```
DATABASE_URL=jdbc:postgresql://your-postgres-host:5432/your_database_name
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
DATABASE_DRIVER=org.postgresql.Driver
```

### Optional Variables (with defaults)

| Variable Name | Default Value | Description |
|---------------|---------------|-------------|
| `APP_NAME` | `ai-tool-summary-generator` | Application name |
| `JPA_DDL_AUTO` | `update` | Hibernate DDL mode |
| `JPA_SHOW_SQL` | `false` | Show SQL queries in logs |
| `JPA_DATABASE_PLATFORM` | `org.hibernate.dialect.MySQL8Dialect` | Database dialect |
| `HIBERNATE_FORMAT_SQL` | `true` | Format SQL output |
| `HIBERNATE_USE_SQL_COMMENTS` | `true` | Add comments to SQL |
| `HIKARI_MAX_POOL_SIZE` | `10` | Max connection pool size |
| `HIKARI_MIN_IDLE` | `5` | Min idle connections |
| `JWT_EXPIRATION` | `86400000` | JWT token expiration (24h) |
| `SERVER_PORT` | `8080` | Server port |
| `CONTEXT_PATH` | `` | Application context path |

### OAuth2 Configuration (Optional)

If you want to use Google OAuth2 authentication:

| Variable Name | Description | How to Get |
|---------------|-------------|------------|
| `GOOGLE_CLIENT_ID` | Google OAuth2 Client ID | Google Cloud Console |
| `GOOGLE_CLIENT_SECRET` | Google OAuth2 Client Secret | Google Cloud Console |
| `GOOGLE_REDIRECT_URI` | OAuth2 redirect URI | `https://your-app.onrender.com/login/oauth2/code/google` |
| `GOOGLE_OAUTH_SCOPE` | `openid,profile,email` | OAuth2 scopes |

## Step 3: Setting Environment Variables on Render

### Method 1: Via Render Dashboard
1. Go to your service dashboard
2. Click on "Environment" tab
3. Click "Add Environment Variable"
4. Add each variable one by one

### Method 2: Via Render CLI
```bash
# Install Render CLI
npm install -g @render/cli

# Login to Render
render login

# Set environment variables
render env set GEMINI_API_KEY=your-api-key
render env set DATABASE_URL=your-database-url
render env set DATABASE_USERNAME=your-username
render env set DATABASE_PASSWORD=your-password
render env set JWT_SECRET=your-jwt-secret
```

## Step 4: Database Setup

### Option A: Use Render's PostgreSQL (Free Tier)
1. Create a new PostgreSQL service on Render
2. Use the provided connection details for your environment variables
3. Update `DATABASE_DRIVER` to `org.postgresql.Driver`
4. Update `JPA_DATABASE_PLATFORM` to `org.hibernate.dialect.PostgreSQLDialect`

### Option B: Use External Database
- AWS RDS
- Google Cloud SQL
- PlanetScale
- Railway
- Supabase

## Step 5: Google OAuth2 Setup (Optional)

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select existing one
3. Enable Google+ API
4. Go to "Credentials" → "Create Credentials" → "OAuth 2.0 Client IDs"
5. Set authorized redirect URIs:
   - `https://your-app.onrender.com/login/oauth2/code/google`
   - `http://localhost:8080/login/oauth2/code/google` (for local development)
6. Copy Client ID and Client Secret to Render environment variables

## Step 6: Gemini API Setup

1. Go to [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Create a new API key
3. Copy the API key to `GEMINI_API_KEY` environment variable

## Step 7: Deploy and Test

1. Push your code to GitHub
2. Render will automatically build and deploy
3. Check the logs for any errors
4. Test your endpoints

## Environment Variables Summary

Here's a complete list of all environment variables you need to set:

```bash
# Required
GEMINI_API_KEY=your-gemini-api-key
DATABASE_URL=jdbc:mysql://your-db-host:3306/your-db-name
DATABASE_USERNAME=your-db-username
DATABASE_PASSWORD=your-db-password
JWT_SECRET=your-super-secret-jwt-key

# Optional (with defaults)
APP_NAME=ai-tool-summary-generator
JPA_DDL_AUTO=update
JPA_SHOW_SQL=false
JPA_DATABASE_PLATFORM=org.hibernate.dialect.MySQL8Dialect
HIBERNATE_FORMAT_SQL=true
HIBERNATE_USE_SQL_COMMENTS=true
HIKARI_MAX_POOL_SIZE=10
HIKARI_MIN_IDLE=5
JWT_EXPIRATION=86400000
SERVER_PORT=8080
CONTEXT_PATH=

# OAuth2 (Optional)
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret
GOOGLE_REDIRECT_URI=https://your-app.onrender.com/login/oauth2/code/google
GOOGLE_OAUTH_SCOPE=openid,profile,email
```

## Troubleshooting

### Common Issues:
1. **Build fails**: Check if all dependencies are in `pom.xml`
2. **Database connection fails**: Verify database URL, credentials, and network access
3. **OAuth2 not working**: Ensure redirect URI matches exactly
4. **JWT errors**: Make sure JWT_SECRET is set and secure

### Check Logs:
- Go to your service dashboard on Render
- Click on "Logs" tab
- Look for error messages and stack traces

## Security Best Practices

1. **Never commit secrets to Git**
2. **Use strong, unique passwords**
3. **Rotate API keys regularly**
4. **Use HTTPS in production**
5. **Set appropriate CORS policies**
6. **Validate all inputs**

## Support

If you encounter issues:
1. Check Render's [documentation](https://render.com/docs)
2. Review Spring Boot logs
3. Verify all environment variables are set correctly
4. Test database connectivity 