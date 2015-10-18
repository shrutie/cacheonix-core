/*
 * Cacheonix systems licenses this file to You under the LGPL 2.1
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.cacheonix.org/products/cacheonix/license-lgpl-2.1.htm
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cacheonix.impl.cache.item;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import junit.framework.TestCase;
import org.cacheonix.impl.net.serializer.Serializer;
import org.cacheonix.impl.net.serializer.SerializerFactory;
import org.cacheonix.impl.util.logging.Logger;

/**
 * Tester for CompressedPartitionElementValue.
 */
public final class CompressedBinaryTest extends TestCase {

   /**
    * Logger.
    *
    * @noinspection UNUSED_SYMBOL, UnusedDeclaration
    */
   private static final Logger LOG = Logger.getLogger(CompressedBinaryTest.class); // NOPMD

   private static final String TEST_VALUE = "test item";

   private CompressedBinary binary = null;


   public void testCreate() {

      assertEquals(TEST_VALUE, binary.getValue());
   }


   public void testNullValue() throws InvalidObjectException {

      final CompressedBinary nullBinary = new CompressedBinary(null);
      assertNull(nullBinary.getValue());
      assertEquals(0, nullBinary.hashCode());
   }


   public void testSerializeDeserialize() throws IOException, ClassNotFoundException {

      final Serializer ser = SerializerFactory.getInstance().getSerializer(Serializer.TYPE_JAVA);
      assertEquals(binary, ser.deserialize(ser.serialize(binary)));
   }


   public void testReadWriteExternal() throws IOException, ClassNotFoundException {

      final ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
      final ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(binary);
      oos.flush();

      assertEquals(binary, new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject());
   }


   public void testEquals() throws InvalidObjectException {

      assertEquals(new CompressedBinary(new Serializable[]{0, 1, 2}),
              new CompressedBinary(new Serializable[]{0, 1, 2}));
   }


   public void testNullEquals() throws InvalidObjectException {

      assertEquals(new CompressedBinary(null), new CompressedBinary(null));
   }


   protected final void setUp() throws Exception {

      super.setUp();
      binary = new CompressedBinary(TEST_VALUE);
   }


   public final String toString() {

      return "CompressedItemTest{" +
              "item=" + binary +
              '}';
   }
}
