package com.king.re0.dao;

//public class UserRepositoryImpl {
//
//    @PersistenceContext
//    EntityManager em;
//    @SuppressWarnings("unchecked")
//    public Page<MemoEntity> search(UserEntity user) {
//        String dataSql = "select t from UserEntity t where 1 = 1";
//        String countSql = "select count(t) from UserEntity t where 1 = 1";
//
//        if(null != user && !StringUtils.isEmpty(user.getName())) {
//            dataSql += " and t.name = ?1";
//            countSql += " and t.name = ?1";
//        }
//
//        Query dataQuery = em.createQuery(dataSql);
//        Query countQuery = em.createQuery(countSql);
//
//        if(null != user && !StringUtils.isEmpty(user.getName())) {
//            dataQuery.setParameter(1, user.getName());
//            countQuery.setParameter(1, user.getName());
//        }long totalSize = (long) countQuery.getSingleResult();
//        Page<UserEntity> page = new Page();
//        page.setTotalSize(totalSize);
//        List<UserEntity> data = dataQuery.getResultList();
//        page.setData(data);
//        return page;
//    }
//
//}
