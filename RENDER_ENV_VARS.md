# Quick Reference: Render Environment Variables

## 🚨 REQUIRED Variables (Must Set)

| Variable | Value | Notes |
|----------|-------|-------|
| `GEMINI_API_KEY` | `AIzaSyC...` | Get from [Google AI Studio](https://makersuite.google.com/app/apikey) |
| `DATABASE_URL` | `jdbc:mysql://host:3306/dbname` | Your database connection URL |
| `DATABASE_USERNAME` | `your_username` | Database username |
| `DATABASE_PASSWORD` | `your_password` | Database password |
| `JWT_SECRET` | `your-super-secret-key` | Any long, random string |

## 📍 Where to Set on Render

1. **Go to**: [Render Dashboard](https://dashboard.render.com/)
2. **Select**: Your web service
3. **Click**: "Environment" tab
4. **Click**: "Add Environment Variable"
5. **Add**: Each variable above

## 🗄️ Database Options

### Option 1: Render PostgreSQL (Free)
```
DATABASE_URL=jdbc:postgresql://your-render-postgres-host:5432/your_db
DATABASE_USERNAME=your_render_username
DATABASE_PASSWORD=your_render_password
DATABASE_DRIVER=org.postgresql.Driver
```

### Option 2: External MySQL
```
DATABASE_URL=jdbc:mysql://your-mysql-host:3306/your_db
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
DATABASE_DRIVER=com.mysql.cj.jdbc.Driver
```

## 🔧 Quick Setup Commands

```bash
# Via Render CLI (if you have it installed)
render env set GEMINI_API_KEY=your-api-key
render env set DATABASE_URL=your-database-url
render env set DATABASE_USERNAME=your-username
render env set DATABASE_PASSWORD=your-password
render env set JWT_SECRET=your-jwt-secret
```

## ✅ Checklist

- [ ] Gemini API key obtained and set
- [ ] Database created and credentials set
- [ ] JWT secret generated and set
- [ ] All variables added to Render dashboard
- [ ] Service deployed successfully
- [ ] Database connection tested
- [ ] API endpoints working

## 🆘 Need Help?

- Check the full guide: `RENDER_DEPLOYMENT.md`
- Review Render logs in dashboard
- Verify all environment variables are set correctly 